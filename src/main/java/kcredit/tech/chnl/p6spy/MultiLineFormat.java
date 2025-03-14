package kcredit.tech.chnl.p6spy;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class MultiLineFormat implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        /* Slf4J 로거를 사용할 때는, Slf4J 에서 시간을 출력하므로 now 삭제 */
        return "# took " + elapsed + "ms | " + category + " | connection " + connectionId + "| url " + url + "\n" + sql +";";
    }
}
