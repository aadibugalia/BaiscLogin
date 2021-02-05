package com.adityabugalia.baiscloginwithrxjava.adapters

import android.R.attr.password
import android.content.Context
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adityabugalia.baiscloginwithrxjava.R
import com.adityabugalia.baiscloginwithrxjava.data.InputModel
import com.adityabugalia.baiscloginwithrxjava.utils.Constants
import com.adityabugalia.baiscloginwithrxjava.viewmodel.LoginViewModel


class LoginAdapter(
    private val context: Context,
    val viewModel: LoginViewModel
) :
    RecyclerView.Adapter<LoginAdapter.ViewHolder>() {


    override fun getItemCount(): Int {
        return viewModel.inputModelList.size
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {


        return ItemViewHolder(
            when (viewType) {
                Constants.LoginViewTypes.TITLE_TEXTVIEW.index -> {
                    LayoutInflater.from(context)
                        .inflate(R.layout.recyclerview_item_textview, viewGroup, false)
                }
                Constants.LoginViewTypes.USERNAME_EDITTEXT.index -> {
                    LayoutInflater.from(context)
                        .inflate(R.layout.recyclerview_item_edittext, viewGroup, false)
                }
                Constants.LoginViewTypes.PASSWORD_EDITTEXT.index -> {
                    LayoutInflater.from(context)
                        .inflate(R.layout.recyclerview_item_edittext, viewGroup, false)
                }
                Constants.LoginViewTypes.SUBMITE_BUTTON.index -> {
                    LayoutInflater.from(context)
                        .inflate(R.layout.recyclerview_item_button, viewGroup, false)
                }


                else -> {
                    LayoutInflater.from(context)
                        .inflate(R.layout.recyclerview_item_button, viewGroup, false)
                }
            }, viewType


        )
    }

    override fun getItemViewType(position: Int): Int {

        return viewModel.inputModelList[position].viewTag.index
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        val vh = viewHolder as ItemViewHolder

        vh.bind(viewModel.inputModelList.get(position))
    }

    open class ViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!)

    inner class ItemViewHolder(itemView: View, viewType: Int) :
        ViewHolder(itemView) {

        private lateinit var txtTitle: TextView
        private lateinit var userNameEditText: EditText
        private lateinit var passwordEditText: EditText
        private lateinit var submitButton: Button
        private var viewTypeLcl = Constants.LoginViewTypes.TITLE_TEXTVIEW
        fun bind(todoItem: InputModel.InputModelItem) {

            when (viewTypeLcl) {

                Constants.LoginViewTypes.TITLE_TEXTVIEW -> {
                    txtTitle.setText(todoItem.displayText)
                }
                Constants.LoginViewTypes.SUBMITE_BUTTON -> {
                    submitButton.setText(todoItem.displayText)
                }
                Constants.LoginViewTypes.USERNAME_EDITTEXT -> {
                    userNameEditText.setHint(todoItem.displayText)

                }
                Constants.LoginViewTypes.PASSWORD_EDITTEXT -> {
                    passwordEditText.setHint(todoItem.displayText)
                    passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)

                }

            }


        }

        init {
            when (viewType) {

                Constants.LoginViewTypes.TITLE_TEXTVIEW.index -> {
                    txtTitle = itemView.findViewById(R.id.textTitle)
                    viewTypeLcl = Constants.LoginViewTypes.TITLE_TEXTVIEW
                }
                Constants.LoginViewTypes.USERNAME_EDITTEXT.index -> {
                    userNameEditText = itemView.findViewById(R.id.inputEditText)
                    viewTypeLcl = Constants.LoginViewTypes.USERNAME_EDITTEXT
                    userNameEditText.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {

                        }

                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {

                            viewModel.onUserNameTextChange(str.toString())
                        }
                    })
                }
                Constants.LoginViewTypes.PASSWORD_EDITTEXT.index -> {
                    passwordEditText = itemView.findViewById(R.id.inputEditText)
                    viewTypeLcl = Constants.LoginViewTypes.PASSWORD_EDITTEXT
                    passwordEditText.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(p0: Editable?) {

                        }

                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {

                        }

                        override fun onTextChanged(str: CharSequence?, p1: Int, p2: Int, p3: Int) {

                            viewModel.onPasswordTextChange(str.toString())
                        }
                    })
                }
                Constants.LoginViewTypes.SUBMITE_BUTTON.index -> {
                    submitButton = itemView.findViewById(R.id.submitButton)
                    viewTypeLcl = Constants.LoginViewTypes.SUBMITE_BUTTON
                    submitButton.setOnClickListener(viewModel)

                }

            }

        }


    }


}