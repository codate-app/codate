package com.donxux.codate.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.donxux.codate.R
import com.donxux.codate.databinding.ActivityMainBinding
import com.donxux.codate.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var navController: NavController

    val motionProgress = Channel<Float>()
    var lockedMotion = false
    var lockedNavigate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMainViewModel()
        setBottomNavigation()
        setRootLayoutTransitionListener()
    }

    fun transitionToStartRootLayout() {
        binding.mainRootLayout.transitionToStart()
    }

    private fun setRootLayoutTransitionListener() {
        binding.mainRootLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                lockedNavigate = true
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {
                if (lockedMotion.not()) {
                    lifecycleScope.launch {
                        motionProgress.send(progress)
                    }
                }
            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                lockedNavigate = false
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) = Unit
        })
    }

    private fun lockMotionOfRootLayout() {
        binding.mainRootLayout.setTransition(R.id.start, R.id.start)
        lockedMotion = true
    }

    private fun unlockMotionOfRootLayout() {
        binding.mainRootLayout.setTransition(R.id.start, R.id.end)
        lockedMotion = false
    }

    private fun setMainViewModel() {
        binding.mainViewModel = mainViewModel
    }

    private fun setBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.mainBottomNavigation.setupWithNavController(navController)

        binding.mainBottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.matching -> {
                    unlockMotionOfRootLayout()
                    it.onNavDestinationSelected(navController)
                }

                else -> {
                    if (lockedNavigate) {
                        false
                    } else {
                        lockMotionOfRootLayout()
                        it.onNavDestinationSelected(navController)
                    }
                }
            }
        }
    }

//    private fun goToLoginActivity() {
//        val intent = Intent(this, LoginActivity::class.java)
//        startActivity(intent)
//        overridePendingTransition(R.anim.slide_up, R.anim.stay_in_place)
//    }

    override fun onDestroy() {
        super.onDestroy()
        motionProgress.close()
    }
}
