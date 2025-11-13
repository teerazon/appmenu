package com.nonthakorn.appmenu.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.nonthakorn.appmenu.R

class MainActivity : AppCompatActivity() {
    //สร้างตัวแปรไว้สำหรับทำงานกับ view binging
    lateinit var  navController: NavController
    lateinit var  appBarConfiguration: AppBarConfiguration
    lateinit var  navHostFragment: NavHostFragment
    lateinit var  mainToolbar: Toolbar
    lateinit var  mainDrawerLayout: DrawerLayout
    lateinit var  mainNevigationView: NavigationView
    lateinit var  mainBottomNavigationView: NavigationView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //ทำการ  Finde View
        mainToolbar = findViewById(R.id.main_toolbar)
        mainDrawerLayout = findViewById(R.id.drawer_layout)
        mainNevigationView = findViewById(R.id.main_navigation_view)
        mainBottomNavigationView = findViewById(R.id.main_bottom_navigation_veiw)

        //กำหนดค่าเริ่มต้นให้กับ navcontroller
        navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host) as NavHostFragment
        navController = navHostFragment.navController

        //กำหนด appBar
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.productFragment,
            R.id.notificationFragment,
            R.id.accountFragment
        ).setOpenableLayout(mainDrawerLayout).build()

        //เรียกการทำงานกับ Toolbar
        setSupportActionBar(mainToolbar)
        setupActionBarWithNavController(navController,appBarConfiguration)

        //เรียกใช้งานเมนูด้านข้าง
        mainBottomNavigationView.setupWithNavController(navController)

        //เรียกใช้งานเมนูด้านล่าง
        mainBottomNavigationView.setupWithNavController(navController)


        }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}