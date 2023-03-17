package com.example.goncharov1.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.goncharov1.R
import com.example.goncharov1.data.MainRepositoryImpl
import com.example.goncharov1.data.mappers.ArticMapper
import com.example.goncharov1.data.mappers.ArticMapperImpl
import com.example.goncharov1.domain.MainRepository
import com.example.goncharov1.domain.getArtic.GetArticUseCase
import com.example.goncharov1.domain.getArtic.GetArticUseCaseImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var articMapper: ArticMapper
    private lateinit var mainRepository: MainRepository
    private lateinit var getArticUseCaseImpl: GetArticUseCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        articMapper = ArticMapperImpl()
        mainRepository = MainRepositoryImpl(articMapper)
        getArticUseCaseImpl = GetArticUseCaseImpl(mainRepository)

        GlobalScope.launch {
            println("!!!!!!!" + getArticUseCaseImpl.getArtic())
        }
    }
}