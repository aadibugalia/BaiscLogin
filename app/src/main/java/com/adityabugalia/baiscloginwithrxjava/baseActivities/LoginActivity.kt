package com.adityabugalia.baiscloginwithrxjava.baseActivities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityabugalia.baiscloginwithrxjava.R
import com.adityabugalia.baiscloginwithrxjava.adapters.LoginAdapter
import com.adityabugalia.baiscloginwithrxjava.data.User
import com.adityabugalia.baiscloginwithrxjava.viewmodel.LoginViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AbstractHeaderActivity<LoginViewModel>() {

    val TAG = LoginActivity::class.java.simpleName

    val compositeDisposable = CompositeDisposable()

    var loginAdapter: LoginAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.onViewReady()
        loginAdapter = LoginAdapter(this, viewModel)
        val mLayoutManager = LinearLayoutManager(applicationContext)

        loginRecyclerView.adapter = loginAdapter
        loginRecyclerView.setLayoutManager(mLayoutManager)
        viewModel.resultLiveData.observe(
            this,
            Observer { result ->

                when (User.getSuccessful()) {

                    true -> {
                        startActivity(
                            Intent(
                                this,
                                DashboardActivity::class.java
                            ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        )
                        this@LoginActivity.finish()
                    }
                    else -> {
                        showDialog(User.errorMessage)
                    }
                }

            }
        )
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setCancelable(true)
            .setPositiveButton(
                android.R.string.yes
            ) { dialog, which ->

            }
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }


    override fun getChildContentView(rootView: ViewGroup): View? {
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.activity_login, rootView, false)
        return view
    }

    override fun initViewModel() {
        // viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        //viewModel.onViewReady()
    }
}