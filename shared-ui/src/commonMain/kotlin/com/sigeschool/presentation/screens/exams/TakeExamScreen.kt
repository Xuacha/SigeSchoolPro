package com.sigeschool.presentation.screens.exams

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.sigeschool.domain.model.Exam
import com.sigeschool.domain.model.Question
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakeExamScreen(
    exam: Exam,
    onFinish: (Double) -> Unit,
    onBack: () -> Unit
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    val answers = remember { mutableStateMapOf<Int, Int>() }
    var timeLeft by remember { mutableStateOf(exam.durationMinutes * 60) }
    var showResults by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0 && !showResults) {
            delay(1.seconds)
            timeLeft--
        } else if (timeLeft == 0) {
            showResults = true
        }
    }

    if (showResults) {
        val score = calculateScore(exam, answers)
        AlertDialog(
            onDismissRequest = { onFinish(score) },
            title = { Text("Examen Finalizado") },
            text = { Text("Tu calificación es: $score / ${exam.maxScore}") },
            confirmButton = {
                Button(onClick = { onFinish(score) }) { Text("Aceptar") }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(exam.title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    Text(
                        text = formatTime(timeLeft),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(end = 16.dp),
                        color = if (timeLeft < 60) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            LinearProgressIndicator(
                progress = { (currentQuestionIndex + 1).toFloat() / exam.questions.size },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(Modifier.height(24.dp))
            
            if (exam.questions.isNotEmpty()) {
                val question = exam.questions[currentQuestionIndex]
                QuestionView(
                    question = question,
                    selectedOption = answers[currentQuestionIndex],
                    onOptionSelected = { answers[currentQuestionIndex] = it }
                )
            }

            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { currentQuestionIndex-- },
                    enabled = currentQuestionIndex > 0
                ) {
                    Text("Anterior")
                }

                if (currentQuestionIndex < exam.questions.size - 1) {
                    Button(onClick = { currentQuestionIndex++ }) {
                        Text("Siguiente")
                    }
                } else {
                    Button(
                        onClick = { showResults = true },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Finalizar")
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionView(
    question: Question,
    selectedOption: Int?,
    onOptionSelected: (Int) -> Unit
) {
    Column {
        Text(text = question.text, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Column(Modifier.selectableGroup()) {
            question.options.forEachIndexed { index, text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (selectedOption == index),
                            onClick = { onOptionSelected(index) },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (selectedOption == index),
                        onClick = null
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}

private fun formatTime(seconds: Int): String {
    val mins = seconds / 60
    val secs = seconds % 60
    return "${mins.toString().padStart(2, '0')}:${secs.toString().padStart(2, '0')}"
}

private fun calculateScore(exam: Exam, answers: Map<Int, Int>): Double {
    var total = 0.0
    exam.questions.forEachIndexed { index, question ->
        if (answers[index] == question.correctOptionIndex) {
            total += question.points
        }
    }
    // Escalar al puntaje máximo si es necesario
    return total
}
