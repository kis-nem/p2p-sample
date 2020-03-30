package com.kisnem.p2p_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.fragment_client.*

class ClientFragment : Fragment() {

    private lateinit var ip: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_client, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendRequest()
    }

    private fun sendRequest() {
        val queue = Volley.newRequestQueue(requireContext())
        val url = "http://whatismyip.akamai.com/"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener {
                ip = it
                textView1.text = ip
            },
            Response.ErrorListener { textView1.text = "That didn't work!" })
        queue.add(stringRequest)
    }

    companion object {
        fun newInstance() = ClientFragment().apply {
            arguments = bundleOf()
        }
    }
}