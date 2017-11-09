package com.bakkenbaeck.mvvm.di

import com.bakkenbaeck.testUtil.NetworkSourceGenerator


class TestModules: ModuleProvider {
    override fun baseUrl() = "http://example.com/"
    override fun networkSource() = NetworkSourceGenerator().networkSource
    override fun scheduler() = TestSchedulerProvider()
}