package com.sigeschool.data.remote

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage

object SupabaseClientProvider {
    private const val SUPABASE_URL = "https://jtaslpuchjciysjqxbnq.supabase.co" 
    private const val SUPABASE_ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp0YXNscHVjaGpjaXlzanF4Ym5xIiwicm9sZSI6ImFub24iLCJpYXQiOjE3ODI4NzAxMjUsImV4cCI6MjA5ODQ0NjEyNX0.VlmyH9WaOtZPcfX4NXcYUaUCApzz4frxzXAsj76803w"

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_ANON_KEY
        ) {
            install(Auth)
            install(Postgrest)
            install(Storage)
            install(io.github.jan.supabase.functions.Functions)
        }
    }
}
