package ru.chuchelin.avg_project.dao;

import org.springframework.stereotype.Component;
import ru.chuchelin.avg_project.models.Report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chuchelin Aleksey
 */
@Component
public class AVGReport {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Report> index() {
        List<Report> reports = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT e.id id,FIO, Dep, vop.Value - avg.AVG_Value AVG_Value\n" +
                    "  FROM employees e\n" +
                    "  JOIN department d on e.DepId = d.id\n" +
                    "  JOIN value_of_pen vop on e.id = vop.EmpId\n" +
                    "  JOIN (select e2.DepId, ROUND(AVG(vop2.Value)) AVG_Value\n" +
                    "                    from employees e2\n" +
                    "                    JOIN value_of_pen vop2\n" +
                    "                      on e2.id = vop2.EmpId\n" +
                    "                   GROUP BY e2.DepId) avg\n" +
                    "    ON avg.DepId = e.DepId\n" +
                    " ORDER BY Dep ASC, FIO ASC";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Report report = new Report();

                report.setId(resultSet.getInt("id"));
                report.setFio(resultSet.getString("FIO"));
                report.setDep(resultSet.getString("Dep"));
                report.setAvg_value(resultSet.getInt("AVG_Value"));

                reports.add(report);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reports;
    }

}
