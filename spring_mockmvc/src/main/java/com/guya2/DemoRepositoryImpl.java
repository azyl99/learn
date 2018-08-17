package com.guya2;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DemoRepositoryImpl implements DemoRepository {
    @Override
    public List<Demo> findDemos(int count) {
        return createDemoList(count);
    }

    private List<Demo> createDemoList(int count) {
        List<Demo> demos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            demos.add(new Demo("Demo" + i + 1));
        }
        return demos;
    }
}
