package com.test.mvvmapp.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

val client = HttpClient(CIO)