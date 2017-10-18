package ir.amv.os.vaseline.basics.apis.jdbc.dialect;

public enum VaselineJdbcDialects implements IVaselineJdbcDialect {
    POSTGRESQL
    ;

    public static VaselineJdbcDialects valueForJdbcDriver(String jdbcDriver) {
        return stringContains(jdbcDriver);
    }

    public static VaselineJdbcDialects valueForHibernateDialect(String hibernateDialect) {
        return stringContains(hibernateDialect);
    }

    private static VaselineJdbcDialects stringContains(final String string) {
        VaselineJdbcDialects[] dialects = VaselineJdbcDialects.values();
        for (VaselineJdbcDialects dialect : dialects) {
            if (string.toLowerCase().contains(dialect.name().toLowerCase())) {
                return dialect;
            }
        }
        return null;
    }

    @Override
    public String getCountAllApproximatelyQuery() {
        switch (this) {
            case POSTGRESQL:
                return "SELECT reltuples AS approximate_row_count FROM pg_class WHERE relname = :TABLE_NAME";
        }
        return null;
    }
}
