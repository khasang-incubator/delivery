package io.delivery.service.impl;

import io.delivery.service.PgDump;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class PgDumpImpl implements PgDump {
    private static final Logger LOGGER = Logger.getLogger(PgDumpImpl.class);
    private Environment environment;

    public PgDumpImpl() {
    }

    public PgDumpImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String dump() {
        String username = environment.getProperty("jdbc.postgresql.username");
        String dbname = environment.getProperty("jdbc.postgresql.databasename");
        String postgre = System.getenv("PG_HOME");
        System.out.println(postgre);

//        String executeCmd = "\"" + postgre + "\\bin\\pg_dump.exe\"" +
//                " -U " + username +
//                " -d " + dbname +
//                " -F p -f D:\\" + dbname + ".sql";

        String executeCmd = "\"" + postgre + "\\bin\\pg_dump.exe\"" +
                " -U " + username +
                " -d " + dbname +
                " -F c -b -v -f D:\\" + dbname + ".dump";


        Process runtimeProcess;
        try {
            runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (Exception ex) {
            LOGGER.error("Something wrong: " + ex);
            ex.printStackTrace();
        }

        return "Dump created";
    }
}

