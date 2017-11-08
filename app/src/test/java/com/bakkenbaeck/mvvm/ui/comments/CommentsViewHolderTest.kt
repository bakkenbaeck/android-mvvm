package com.bakkenbaeck.mvvm.ui.comments

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.bakkenbaeck.mvvm.di.ModuleProvider
import com.bakkenbaeck.mvvm.di.Modules
import com.bakkenbaeck.mvvm.di.TestModules
import com.bakkenbaeck.mvvm.model.data.Comment
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class CommentsViewHolderTest {
    private lateinit var dependencies: ModuleProvider
    private lateinit var viewModel: CommentsViewModel
    private lateinit var observer: Observer<List<Comment>>

    @Rule @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        dependencies = TestModules()
        Modules.init(dependencies)
        viewModel = CommentsViewModel()
        observer = Mockito.mock(Observer::class.java) as Observer<List<Comment>>
    }

    @Test
    fun commentsAreBroadcastOnLiveData() {
        viewModel.comments.observeForever(observer)
        assertNotNull(viewModel.comments.value)
    }
}