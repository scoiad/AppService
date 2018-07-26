package com.coiad.appservice.task;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.coiad.appservice.bean.AnniversaryBean;
import com.coiad.appservice.dao.AnniversaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class TestTask {

    private final static String MASTER_SECRET = "6225095981be8c4af13371d5";
    private final static String APP_KEY = "48759b1b893c001db450f338";

    @Autowired
    private AnniversaryDao dao;

    @Scheduled(cron = "0 0 8 * * ?")
    public void test() {
        List<AnniversaryBean> mList = dao.getAllList();
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        for (AnniversaryBean bean : mList) {
            if (bean.isHundredFlag()) {
                if (isHundred(bean.getHundredDate())) {
                    send_alert(jPushClient, bean.getPhone(), bean.getAnniversaryName()
                            + " 一百天了！", "百日纪念");
                }
            }
            if (bean.isMonthFlag()) {
                if (monthCount(bean.getAnniversaryDate()) > 0) {
                    send_alert(jPushClient, bean.getPhone(), bean.getAnniversaryName() + "第"
                            + monthCount(bean.getAnniversaryDate()) + "个月纪念", "月度纪念");
                }
            }
            if (bean.isYearFlag()) {
                if (yearCount(bean.getAnniversaryDate()) > 0) {
                    send_alert(jPushClient, bean.getPhone(), bean.getAnniversaryName() + "第"
                            + yearCount(bean.getAnniversaryDate()) + "周年纪念", "周年纪念");
                }
            }
        }
    }

    private static int monthCount(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        if (calendar.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
            return (now.get(Calendar.YEAR) - calendar.get(Calendar.YEAR)) * 2
                    + (now.get(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH));
        } else {
            return -1;
        }
    }

    private static int yearCount(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        if (calendar.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
            return now.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        } else {
            return -1;
        }
    }

    private static boolean isHundred(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        return calendar.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)
                && calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                && calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR);

    }


    private void send_alert(JPushClient jPushClient, String id, String message, String title) {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(id))
                .setNotification(Notification.newBuilder()
                        .setAlert(message)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(message)
                                .setTitle(title)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(message)
                                .build())
                        .build())
                .build();
        try {
            PushResult result = jPushClient.sendPush(payload);
            System.out.println("Got result - " + result);
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }
}
