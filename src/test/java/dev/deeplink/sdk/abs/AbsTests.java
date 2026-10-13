package dev.deeplink.sdk.abs;

import dev.deeplink.sdk.ApiContext;
import org.junit.jupiter.api.BeforeAll;

/**
 * Created by jack on 2024/08/06
 * email: jack@dlink.cloud
 *
 * @author jack
 */
public class AbsTests {
    public static ApiContext CONTEXT;

    @BeforeAll
    static void beforeAll() {
        CONTEXT = ApiContext.create("{App Code}", "{API Token}")
                .setApiUrl("https://api.deeplink.dev/");
        ;
    }
}
