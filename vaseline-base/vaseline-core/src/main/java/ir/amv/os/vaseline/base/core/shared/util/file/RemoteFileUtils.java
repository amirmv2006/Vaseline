package ir.amv.os.vaseline.base.core.shared.util.file;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.vfs.*;
import org.apache.commons.vfs.auth.StaticUserAuthenticator;
import org.apache.commons.vfs.impl.DefaultFileSystemConfigBuilder;

import java.io.*;

/**
 * Created by AMV on 1/10/2016.
 */
public class RemoteFileUtils {

    public static boolean mkDir(String host, String username, String password, String path) {
        try {
            if (host.equals("localhost") || host.equals("127.0.0.1")) {
                return new File(path).mkdirs();
            } else {
                if (path.startsWith("/")) { // linux
                    return createRemoteDirectoryLinux(host, username, password, path);
                } else { // windows
                    return createRemoteDirectoryWindows(host, username, password, path);
                }
            }
        } catch (JSchException e) {
            return false;
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (FileSystemException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean createFile(String host, String user, String password, String serverFilePath, String localFilePath) {
        try {
            if (host.equals("localhost") || host.equals("127.0.0.1")) {
                FileInputStream input = new FileInputStream(localFilePath);
                FileOutputStream output = new FileOutputStream(serverFilePath);
                IOUtils.copy(input, output);
                input.close();
                output.flush();
                output.close();
                return true;
            } else {
                if (serverFilePath.startsWith("/")) { // linux
                    createRemoteFileLinux(host, user, password, serverFilePath, localFilePath);
                    return true;
                } else { // windows
                    createRemoteFileWindows(host, user, password, serverFilePath, localFilePath);
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean createRemoteFileWindows(String host, String username, String password, String path, String localFilePath) throws FileSystemException {
        if (path.matches("(^\\w)(:)(.*)")) {
            path = "\\\\" + host + "\\" + path.replaceFirst("(^\\w)(:)(.*)", "$1\\$$3");
        }
        FileSystemManager manager = VFS.getManager();
        String domain = null;
        if (username.contains("@")) {
            domain = username.substring(username.indexOf("@") + 1);
            username = username.substring(0, username.indexOf("@"));
        }
        UserAuthenticator auth = new StaticUserAuthenticator(domain, username, password);
        FileSystemOptions opts = new FileSystemOptions();
        DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);


        FileObject fo = manager.resolveFile(path, opts);

        createRecursively(fo.getParent());
        fo.copyFrom(manager.resolveFile("file://" + localFilePath), Selectors.SELECT_SELF);
        return fo.exists();
    }

    private static boolean createRemoteDirectoryWindows(String host, String username, String password, String path) throws FileSystemException {
        if (path.matches("(^\\w)(:)(.*)")) {
            path = "\\\\" + host + "\\" + path.replaceFirst("(^\\w)(:)(.*)", "$1\\$$3");
        }
        FileObject destn = VFS.getManager().resolveFile(path);
        String domain = null;
        if (username.contains("@")) {
            domain = username.substring(username.indexOf("@") + 1);
            username = username.substring(0, username.indexOf("@"));
        }
        UserAuthenticator auth = new StaticUserAuthenticator(domain, username, password);
        FileSystemOptions opts = new FileSystemOptions();
        DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);


        FileObject fo = VFS.getManager().resolveFile(path, opts);

        createRecursively(fo);
        return fo.exists();
    }

    private static boolean createRemoteDirectoryLinux(String host, String username, String password, String path) throws JSchException, SftpException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host);
        session.setUserInfo(new JSchUserInfo(password));
        session.connect();

        Channel channel = session.openChannel("sftp");
        channel.connect();
        ChannelSftp sftp = (ChannelSftp) channel;

        String[] complPath = path.split("/");
        sftp.cd("/");
        for (String dir : complPath) {
            if (dir.equals("")) {
                continue;
            }
            try {
                sftp.cd(dir);
            } catch (SftpException e2) {
                sftp.mkdir(dir);
                sftp.cd(dir);
            }
        }
        String pwd = sftp.pwd();
        sftp.disconnect();
        session.disconnect();
        return pwd.equals(path);
    }

    private static void createRecursively(FileObject fo) throws FileSystemException {
        if (!fo.getParent().exists()) {
            createRecursively(fo.getParent());
        }
        if (!fo.exists()) {
            fo.createFolder();
        }
    }

    public static void main(String[] args) {
        RemoteFileUtils.createFile("192.168.5.143", "Abdollahzadeh", "amnafzar", "C:\\solr-4.10.4-old\\example\\resources\\amv\\test\\go\\test.pdf", "C:\\Users\\AMV\\Downloads\\Documents\\jOOQ-manual-3.6.pdf");
    }

    private static void createRemoteFileLinux(String host, String user, String password, String serverFilePath, String localFilePath) throws JSchException, IOException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, 22);

        UserInfo ui = new JSchUserInfo(password);
        session.setUserInfo(ui);
        session.connect();

        boolean ptimestamp = true;

        String command = "scp " + (ptimestamp ? "-p" : "") + " -t " + serverFilePath;
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(command);

        // get I/O streams for remote scp
        OutputStream out = channel.getOutputStream();
        InputStream in = channel.getInputStream();

        channel.connect();

        if (checkAck(in) != 0) {
            System.exit(0);
        }

        File _lfile = new File(localFilePath);

        if (ptimestamp) {
            command = "T" + (_lfile.lastModified() / 1000) + " 0";
            // The access time should be sent here,
            // but it is not accessible with JavaAPI ;-<
            command += (" " + (_lfile.lastModified() / 1000) + " 0\n");
            out.write(command.getBytes());
            out.flush();
            if (checkAck(in) != 0) {
                System.exit(0);
            }
        }

        // send "C0644 filesize filename", where filename should not include '/'
        long filesize = _lfile.length();
        command = "C0644 " + filesize + " ";
        if (localFilePath.lastIndexOf('/') > 0) {
            command += localFilePath.substring(localFilePath.lastIndexOf('/') + 1);
        } else {
            command += localFilePath;
        }
        command += "\n";
        out.write(command.getBytes());
        out.flush();
        if (checkAck(in) != 0) {
            System.exit(0);
        }

        // send a content of lfile
        FileInputStream fis = new FileInputStream(localFilePath);
        byte[] buf = new byte[1024];
        while (true) {
            int len = fis.read(buf, 0, buf.length);
            if (len <= 0) break;
            out.write(buf, 0, len); //out.flush();
        }
        fis.close();
        fis = null;
        // send '\0'
        buf[0] = 0;
        out.write(buf, 0, 1);
        out.flush();
        if (checkAck(in) != 0) {
            System.exit(0);
        }
        out.close();

        channel.disconnect();
        session.disconnect();
    }

    private static int checkAck(InputStream in) throws IOException {
        int b = in.read();
        // b may be 0 for success,
        //          1 for error,
        //          2 for fatal error,
        //          -1
        if (b == 0) return b;
        if (b == -1) return b;

        if (b == 1 || b == 2) {
            StringBuffer sb = new StringBuffer();
            int c;
            do {
                c = in.read();
                sb.append((char) c);
            }
            while (c != '\n');
            if (b == 1) { // error
                System.out.print(sb.toString());
            }
            if (b == 2) { // fatal error
                System.out.print(sb.toString());
            }
        }
        return b;
    }
}

