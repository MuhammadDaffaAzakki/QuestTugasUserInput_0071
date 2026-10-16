package com.example.tugas5

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration.Companion.seconds

@Composable
fun FormBiodataScreen() {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    var durationDropdownExpanded by remember { mutableStateOf(value = false) }

    var facilityBola by remember { mutableStateOf(value = false) }
    var facilityWasit by remember { mutableStateOf(value = false) }
    var facilityRompi by remember { mutableStateOf(value = false) }

    val durationOptions = listOf(
        stringResource(R.string.duration_option_1),
        stringResource(R.string.duration_option_2),
        stringResource(R.string.duration_option_3),
        stringResource(R.string.duration_option_4),
    )

    val facilityBolaText = stringResource(R.string.facility_bola)
    val facilityWasitText = stringResource(R.string.facility_wasit)
    val facilityRompiText = stringResource(R.string.facility_rompi)
    val noFacilityText = stringResource(R.string.no_facility)

    val isFormComplete = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            email.isNotBlank() &&
            dob.isNotBlank() &&
            duration.isNotBlank() &&
            (facilityBola || facilityWasit || facilityRompi)

    val buttonBgColor = if (isFormComplete) {
        colorResource(R.color.button_blue_active)
    } else {
        colorResource(R.color.button_bg)
    }

    val buttonTextColor = if (isFormComplete) {
        colorResource(R.color.button_blue_text)
    } else {
        colorResource(R.color.button_text)
    }

    val selectedFacilities = mutableListOf<String>()
    if (facilityBola) selectedFacilities.add(facilityBolaText)
    if (facilityWasit) selectedFacilities.add(facilityWasitText)
    if (facilityRompi) selectedFacilities.add(facilityRompiText)

    val facilitiesResult = if (selectedFacilities.isEmpty()) {
        noFacilityText
    } else {
        selectedFacilities.joinToString(", ")
    }

    val formattedMessage = stringResource(
        id = R.string.snackbar_format,
        firstName,
        lastName,
        email,
        dob,
        duration,
        facilitiesResult,
    )

    fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                dob = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, month + 1, year)
            },
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH],
        )
        datePickerDialog.show()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = colorResource(R.color.scaffold_bg),
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            }
    }
}