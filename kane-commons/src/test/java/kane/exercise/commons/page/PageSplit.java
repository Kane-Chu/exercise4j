package kane.exercise.commons.page;

import org.junit.Test;

/**
 * 分页操作
 * 将原有分页参数（开始页 结束页 页大小）按照最小页大小进行重新分割
 *
 * @author kane
 */
public class PageSplit {
    @Test
    public void test() {
        int startPage = 12;
        int endPage = 100;
        int pageSize = 10;

        calculate(startPage, endPage, pageSize);

    }

    public void calculate(int startPage, int endPage, int pageSize) {
        // 最小页大小
        int minPageSize = 100;
        // 结束记录
        int endCount = endPage * pageSize;
        // 开始记录
        int startCount = (startPage - 1) * pageSize + 1;

        int fromPage, toPage, pageRows;

        if (pageSize >= minPageSize) {
            // 若传入的页大小大于等于最小页大小 则不需重新分隔
            fromPage = startPage;
            toPage = endPage;
            pageRows = pageSize;
        } else {
            // 若传入的页大小小于最小页大小 则重新分隔
            pageRows = minPageSize;
            if (startCount < minPageSize) {
                // 若开始记录小于等于最小页大小 则从第一页开始查询
                fromPage = 1;
            } else {
                // 重新计算开始页
                fromPage = startCount / pageRows + 1;
            }
            // 重新计算结束页
            toPage = endCount % pageRows == 0 ? endCount / pageRows : endCount / pageRows + 1;
        }
        // 取数据
        for (int i = fromPage; i <= toPage; i++) {
            int start = (i - 1) * pageRows + 1;
            if (start < startCount) {
                // 若当前开始记录小于原始开始记录 则开始记录为原始开始记录 即过滤掉不需要的数据
                start = startCount;
            }
            int end = i * pageRows;
            System.out.println(start + "-" + end);
        }


    }
}