package com.example.instaclone.views.pager_fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dymagram.viewmodel.factories.HomeFeedViewModelFactory
import com.example.instaclone.R
import com.example.instaclone.data.model.messages.Message
import com.example.instaclone.repositories.MessagesRepository
import com.example.instaclone.viewmodel.MessagesViewModel
import com.example.instaclone.viewmodel.factories.DirectMessagesViewModelFactory
import com.example.instaclone.views.recycler_view_adapters.dm_adapters.DirectMessagesRvAdapter

class DirectMessagesFragment : Fragment() {

    private lateinit var messagesRv: RecyclerView

    private val messagesViewModel: MessagesViewModel by viewModels {
        DirectMessagesViewModelFactory(MessagesRepository(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_direct_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData(view)
    }

    private fun setUpDmRv(messages: List<Message>, fragmentView: View)  {
        this.messagesRv = fragmentView.findViewById(R.id.direct_messages_rv)
        this.messagesRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.messagesRv.adapter = DirectMessagesRvAdapter(messages)
    }

    private fun fetchData(fragmentView: View) {
        this.messagesViewModel.messagesData.observe(viewLifecycleOwner) { messages ->
            setUpDmRv(messages, fragmentView)
        }

        this.messagesViewModel.fetchMessages()
    }

}