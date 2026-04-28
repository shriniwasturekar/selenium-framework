package org.shriniwas.utils;

public final class EmailBody {

    private EmailBody() {}

    public static String buildExecutionSummary(
            String env,
            String browser,
            int total,
            int passed,
            int failed,
            int skipped) {

        StringBuilder body = new StringBuilder();

        body.append("<html>");
        body.append("<body style='font-family: Arial;'>");

        body.append("<h2>Automation Execution Summary</h2>");

        body.append("<table border='1' cellpadding='10' cellspacing='0' style='border-collapse: collapse;'>");

        body.append("<tr style='background-color:#f2f2f2;'>");
        body.append("<th>Environment</th>");
        body.append("<th>Browser</th>");
        body.append("<th>TotalTCS</th>");
        body.append("<th>PassedTCS</th>");
        body.append("<th>FailedTCS</th>");
        body.append("<th>SkippedTCS</th>");
        body.append("</tr>");

        body.append("<tr>");
        body.append("<td>").append(env.toUpperCase()).append("</td>");
        body.append("<td>").append(browser.toUpperCase()).append("</td>");
        body.append("<td>").append(total).append("</td>");

        body.append("<td style='color:green;font-weight:bold;'>")
                .append(passed)
                .append("</td>");

        body.append("<td style='color:red;font-weight:bold;'>")
                .append(failed)
                .append("</td>");

        body.append("<td style='color:orange;font-weight:bold;'>")
                .append(skipped)
                .append("</td>");

        body.append("</tr>");

        body.append("</table>");

        body.append("<br><br>");
        body.append("Please find attached <b>Extent Report</b>.");

        body.append("</body>");
        body.append("</html>");

        return body.toString();
    }
}