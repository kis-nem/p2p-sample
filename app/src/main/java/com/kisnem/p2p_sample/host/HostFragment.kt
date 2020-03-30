package com.kisnem.p2p_sample.host

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.kisnem.p2p_sample.R
import kotlinx.android.synthetic.main.fragment_host.*
import java.io.DataInputStream
import java.net.ServerSocket
import java.net.Socket

class HostFragment private constructor() : Fragment() {

    private var server: ServerSocket? = null
    private var client: Socket? = null
    private var textRunnable: Runnable? = null
    private val textHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        server = ServerSocket(6666).apply {
            client = accept()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inn = client?.let { client ->
            DataInputStream(client.getInputStream())
        }
        textRunnable = Runnable {
            val answer = inn?.readUTF()
            tvStatus.text = answer
            textRunnable?.let { runnable -> textHandler.postDelayed(runnable, 5000) }
        }
    }

    companion object {
        fun newInstance(): HostFragment {
            return HostFragment().apply {
                arguments = bundleOf()
            }
        }
    }

    override fun onDestroyView() {
        server?.close()
        textHandler.removeCallbacks(textRunnable)
        super.onDestroyView()
    }
}
