package com.guya2;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

@Component
public class DemoTask {

    static int a = 1;

    /**
     * Cron表达式由6或7个空格分隔的时间字段组成：秒 分钟 小时 日期 月份 星期 年
     *
     * 字段　　允许值　　允许的特殊字符
     * 秒     　 0-59 　　　　, - * /
     * 分     　 0-59　　　　 , - * /
     * 小时      0-23 　　　　, - * /
     * 日期      1-31 　　　　, - * ? / L W C
     * 月份      1-12 　　　　, - * /
     * 星期      1-7 　　　　  , - * ? / L C # 【1是周日！2是周一，以此类推】
     * 年     1970-2099 　　, - * /
     *
     * “*”字符被用来指定所有的值。如："*"在分钟的字段域里表示“每分钟”。
     * “?”字符只在日期域和星期域中使用。它被用来指定“非明确的值”。
     * “-”字符被用来指定一个范围。如：“10-12”在小时域意味着“10点、11点、12点”。
     * “,”字符被用来指定另外的值。如：“MON,WED,FRI”在星期域里表示”星期一、星期三、星期五”。
     * “/”字符用于指定增量。如：“0/15”在秒域意思是每分钟的0，15，30和45秒。
     * L是‘last’的省略写法。在day-of- month域中表示一个月的最后一天。如果在day-of-week域中前面加上数字，它表示 一个月的最后几天，例如‘6L’就表示一个月的最后一个星期五。
     * 字符“W”只允许日期域出现。这个字符用于指定日期的最近工作日。例如：如果你在日期域中写 “15W”，表示：这个月15号最近的工作日。所以，如果15号是周六，则任务会在14号触发。如果15好是周日，则任务会在周一也就是16号触发。
     * 字符“#”只允许在星期域中出现。这个字符用于指定本月的某某天。例如：“6#3”表示本月第三周的星期五（6表示星期五，3表示第三周）
     *
     * 例子：
0 0 10,14,16 * * ?  每天上午10点，下午2点，4点
0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时
0 * 14 * * ?        在每天下午2点到下午2:59期间的每1分钟触发
0 0/5 14 * * ?      在每天下午2点到下午2:55期间的每5分钟触发
0 0-5 14 * * ?      在每天下午2点到下午2:05期间的每1分钟触发
0 15 10 L * ?       每月最后一日的上午10:15触发
0 15 10 ? * 6#3     每月的第三个星期五上午10:15触发
     *
     */
    @Scheduled(cron = "0/25 * * * * ? ")// 每分钟的第0、25、50秒执行一次
    public void helloTask() {
        System.out.println("Hello");
    }

    @Scheduled(fixedRate = 5*1000)// 自启动开始，每隔5秒执行一次
    public void heyTask() throws InterruptedException {
        long t1 = System.currentTimeMillis() / 1000;
        System.out.println("Hey " + a++);
        TimeUnit.SECONDS.sleep(12);// 这个定时任务结束之前，是不会启动下一个定时任务的
        System.out.println("Bye " + a);
    }
}
