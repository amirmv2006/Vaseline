@Library('laas-jenkins-lib') _

def pipeline = new ir.amv.enterprise.laas.tools.jenkins.lib.AmirBuilder()
pipeline.execute([
    "IS_MAVEN_DEPLOY":true,
    "MAVEN_DEPLOY_CREDENTIALS" : "jenkins-nexus-password"
])