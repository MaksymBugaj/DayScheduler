package com.example.dayscheduler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dayscheduler.navigation.Navigation
import com.example.dayscheduler.ui.theme.DaySchedulerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaySchedulerTheme {
                MainScreenView()
            }
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }, content = { paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())) {
                Navigation(navController)
            }
        }
    )
}

@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Current,
        BottomNavItem.CompletedTasks,
        BottomNavItem.CreateSchedule
    )
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 10.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
/**
 * Create schedule powinien:
 * wyświetlić dodatkowy ekran
 * pozwolić userowi wybrać dni w które chce żeby dostał przypomnienie
 * pozwolić userowi wybrać godziny przypomnień
 */


/**
 * quick roadmap to achieve and technologys to do:
 * tworzenie schedulera
 * notifications ala alarm implement
 * ustalenie kiedy zakonczu sie schedule na dany dzien -> tzn powiadomienie o koncu dnia
 * start schedula na dany dzien |
 * wyswietlanie taskow do ukonczenia w danym dniu i timera do konca |
 * gdy dojdzie do konca dnia, należy wyświetlić powiadomienie o końcu dnia i
 * po kliknieciu w nie, przekierowac usera do taskow do zakonczenia i dac mu mozliwosc zakonczenia dnia |
 * po zakonczeniu dnia, nalezy odznaczyc taski jako zakonczone i wyswietlic procentowo ile sie udalo ukonczyc |
 *
 *
 *
 */

/** teknology
 * coroutines -> proponuje pobawic sie jobami by lepiej zrozumiec co i jak smiga
 * work manager
 * notifications
 * jetpack compose
 * github ci/cd with releases
 * ci/cd to play store
 */

/**
 * cel powyzszych to koniec 22 pazdziernika
 * wstepnie proponuje 5h (8->9:15| 10->12| 15-> 16:45?)
 */

/**
 * aktualnie jest 6.12
 * create schedule został wstępnie osiągnięty
 */

/**
 * aktualnie jest 19.12
 * zmiana założeń na zrobienie prostego day schedulera na każdy dzień
 * dosłownie wchodzisz, dodajesz taski na dzień dzisiejszy (tworzysz albo dodajesz taski z poprzednich dni)
 * i każdy ma checkboxa czy został ukończony
 *
 */

/**
 * update 6.01.2023
 * kopia alka app
 *
 * -> current : no tasks for today. Click and select tasks you would like to see for today -> navigates to add
 * _. add zostaje niezmienione, aczkolwiek kliknięcie plus tworzy new schedule
 * który jest wyświetlany na current
 */

/**
 * update 19.01.2023
 *
 * current task dostaje flage active/finished
 * current schedule ma mieć flagę że jest aktywny, wtedy jeśli jakieś zadanie dodajemy, to dodajemy do aktywnego schedula
 * taski do dodania powinny być filtrowane w taki sposób, by nie można było dodać już aktywnego zadania
 *
 * problem is:
 * currentschedule view powinien aktualnie posiadać model z flagą is active, co powinno byc aktualizowane w bazie
 *
 * update 23.01
 * no to trzeba zmienić nazwę na procrastinating finisher
 * nastepnie dodac cele na dzis
 * do godzin
 * tabelke z karami
 * i z wygranymi
 *
 *
 * update 26
 *
 * taskschedule entity
 * if current tasks list is empty && schedule.tasks.isNotEmpty then show button with finish schedule
 *
 *
 * update 7.06 xd
 * no to mamy wyświetlanie tasków na dzień dzisiejszy, kończenie tasków itp itd. Można powiedzieć że wszystko śmiga
 * - pozmieniać nazwy na current, completed, create
 * - pozbyć się settings
 * - zmienić ikony do kończenia zadań, jak również kolor tła
 * - dodać nagłówek w completed
 * - dodać ładowanie tasków
 *
 * update 8.06
 * - sklep release
 */