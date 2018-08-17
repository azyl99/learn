package com.guya2;

import java.util.List;

public interface DemoRepository {
    List<Demo> findDemos(int count);
}
