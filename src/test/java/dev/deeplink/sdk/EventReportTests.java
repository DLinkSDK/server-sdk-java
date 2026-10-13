package dev.deeplink.sdk;

import cn.hutool.json.JSONUtil;
import dev.deeplink.sdk.abs.AbsTests;
import dev.deeplink.sdk.ads.ActionSourceEnum;
import dev.deeplink.sdk.ads.EventTypeEnum;
import dev.deeplink.sdk.ads.dto.EventReportItem;
import dev.deeplink.sdk.ads.ro.EventReportRequest;
import dev.deeplink.sdk.ads.vo.EventReportResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EventReportTests extends AbsTests {

    private static final String DEEP_LINK_ID = "40EB0987-F956-43AC-9119-A82BD25900CC";
    private static final String ACCESS_USER_ID = "user_123";

    @Test
    @DisplayName("ViewContentEvent — 浏览商品")
    public void testViewContentEvent() {
        EventReportRequest request = base(EventTypeEnum.ViewContentEvent, "sess_view_");
        request.setCustomData(single("sku_001", "TestProduct", 299F, 1L, "USD", 299F));
        execute(request);
    }

    @Test
    @DisplayName("AddToCartEvent — 加购")
    public void testAddToCartEvent() {
        EventReportRequest request = base(EventTypeEnum.AddToCartEvent, "sess_cart_");
        request.setCustomData(single("sku_001", "TestProduct", 99F, 1L, "USD", 99F));
        execute(request);
    }

    @Test
    @DisplayName("AddToWishlistEvent — 收藏")
    public void testAddToWishlistEvent() {
        EventReportRequest request = base(EventTypeEnum.AddToWishlistEvent, "sess_wish_");
        request.setCustomData(single("sku_001", "TestProduct", 99F, 1L, "USD", 99F));
        execute(request);
    }

    @Test
    @DisplayName("SearchEvent — 搜索")
    public void testSearchEvent() {
        EventReportRequest request = base(EventTypeEnum.SearchEvent, "sess_search_");
        EventReportItem.OrderCustom data = single("sku_001", "TestProduct", 99F, 1L, "USD", 99F);
        data.setSearchString("蓝牙TestProduct");
        request.setCustomData(data);
        execute(request);
    }

    @Test
    @DisplayName("InitiateCheckoutEvent — 发起结账")
    public void testInitiateCheckoutEvent() {
        EventReportRequest request = base(EventTypeEnum.InitiateCheckoutEvent, "sess_checkout_");
        // contents[].value 为单价 99，顶层 value = 99 × 2 = 198
        request.setCustomData(single("sku_001", "TestProduct", 99F, 2L, "USD", 198F));
        execute(request);
    }

    @Test
    @DisplayName("PurchaseEvent — 购买")
    public void testPurchaseEvent() {
        EventReportRequest request = base(EventTypeEnum.PurchaseEvent, "ORD-");
        request.setCustomData(single("sku_001", "TestProduct", 299F, 1L, "USD", 299F));
        execute(request);
    }

    @Test
    @DisplayName("SubscribeEvent — 订阅")
    public void testSubscribeEvent() {
        EventReportRequest request = base(EventTypeEnum.SubscribeEvent, "SUB-");
        EventReportItem.OrderCustom data = single("plan_monthly", "月度会员", 29.9F, 1L, "USD", 29.9F);
        data.setSubscribeDay("30");
        request.setCustomData(data);
        execute(request);
    }

    @Test
    @DisplayName("UserDetailChangedEvent — 用户资料变更")
    public void testUserDetailChangedEvent() {
        EventReportRequest request = base(EventTypeEnum.UserDetailChangedEvent, "sess_user_");
        request.setCustomData(null);
        request.setUserData(new EventReportItem.UserData()
            .setEmails(Collections.singletonList("user@example.com")));
        execute(request);
    }

    private static EventReportRequest base(EventTypeEnum type, String eventIdPrefix) {
        long now = System.currentTimeMillis();
        EventReportRequest request = new EventReportRequest();
        request.setEventId(eventIdPrefix + now);
        request.setEventTime(now);
        request.setEventType(type.getCode());
        request.setActionSource(ActionSourceEnum.website.getCode());
        request.setDeepLinkId(DEEP_LINK_ID);
        request.setAccessUserId(ACCESS_USER_ID);
        request.setAppData(new EventReportItem.AppData()
            .setOsVersion("10/11")
            .setPlatform("ios")
            .setLocale("en-US")
            .setTimezone("GMT-4:00"));
        return request;
    }

    private static EventReportItem.OrderCustom single(
        String productId, String productName, float unitPrice, long qty, String currency, float totalValue
    ) {
        EventReportItem.OrderCustom data = new EventReportItem.OrderCustom();
        data.setCurrency(currency);
        data.setValue(totalValue);
        data.setContents(Collections.singletonList(
            new EventReportItem.OrderCustom.Content()
                .setProductId(productId)
                .setProductName(productName)
                .setQuantity(qty)
                .setValue(unitPrice)
        ));
        return data;
    }

    private static void execute(EventReportRequest request) {
        Result<EventReportResponse> result = CONTEXT.execute(request);
        System.out.println(request.getEventType() + " => " + JSONUtil.toJsonStr(result));
        assertNotNull(result);
        assertTrue(Boolean.TRUE.equals(result.getSuccess()), result.getMessage());
    }
}
