package com.p6spy.engine.spy.appender;

public class MultiLineFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement t : Thread.currentThread().getStackTrace()) {
            String cn = t.getClassName();
            if (cn.startsWith("kcredit."))
                sb.append("/* %s.%s.%d */\n".formatted(cn, t.getMethodName(), t.getLineNumber()));
        }
        sql = sql.replaceAll("(?:[ \\t]*(\\R))+", "$1");
        sql = sb.append(sql).toString();
        /* Slf4J 로거를 사용할 때는, Slf4J 에서 시간을 출력하므로 now 삭제 */
        return "# took " + elapsed + "ms | " + category + " | connection " + connectionId + "| url " + url + "\n" + sql + ";";
    }
}
