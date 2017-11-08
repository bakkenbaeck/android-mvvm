package com.bakkenbaeck.mvvm.di

import com.bakkenbaeck.testUtil.NetworkSourceGenerator


class TestModules: ModuleProvider {
    override fun scheduler() = TestSchedulerProvider()
    override fun networkSource() = NetworkSourceGenerator().networkSource
}