package com.pd.githubclient.domain

import com.pd.githubclient.data.User

//создаем интерфейс для того чтобы отлеживание на нажатие можно было пробросить дальше в адаптер
interface OnItemClickListener {

    fun onItemClick(user: User)

}
