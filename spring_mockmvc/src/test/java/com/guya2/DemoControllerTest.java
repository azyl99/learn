package com.guya2;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.List;

public class DemoControllerTest {

    DemoController controller;
    List<Demo> expectedDemos;

    @Before
    public void before() {
        expectedDemos = createDemoList(5);
        DemoRepository mockRepository = Mockito.mock(DemoRepository.class);
        Mockito.when(mockRepository.findDemos(5))
                .thenReturn(expectedDemos);
        controller = new DemoController(mockRepository);
    }

    /**
     * 最原始的测试
     */
    @Test
    public void testIndex() {
        Assert.assertEquals("index", controller.index());
    }

    @Test
    public void shouldShowDemos() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/resources/templates/demos.html"))
                .build();

        mockMvc.perform(MockMvcRequestBuilders.get("/demos"))
                .andExpect(MockMvcResultMatchers.view().name("demos"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("demoList"))
        ;//.andExpect(MockMvcResultMatchers.model().attribute("demoList",CoreMatchers.hasItem(expectedDemos)));
    }

    private List<Demo> createDemoList(int count) {
        List<Demo> demos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            demos.add(new Demo("Demo" + i + 1));
        }
        return demos;
    }
}
