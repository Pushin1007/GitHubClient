package com.pd.githubclient.data

import com.pd.githubclient.domain.User

//создаем интерфейс для того чтобы отлеживание на нажатие можно было пробросить дальше в адаптер
interface OnItemClickListener {

    fun onItemClick(user: User)

}
