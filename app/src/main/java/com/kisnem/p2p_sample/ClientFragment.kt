package com.kisnem.p2p_sample

import android.os.Bundle
import android.os.Handler
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
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket

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

        button.setOnClickListener {
            sendMessage(editText_msg.text.toString(), editText_ip.text.toString())
        }
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

    private fun sendMessage(msg: String, hostIp: String) {
        val handler = Handler();
        val thread = Thread(Runnable {
            try {
                val s = Socket(hostIp, 6666);
                val out = s.getOutputStream();
                val output = PrintWriter(out);
                output.println(msg);
                output.flush();
                val input = BufferedReader(InputStreamReader(s.getInputStream()));
                val st = input.readLine();
                handler.post {
                    run {
                        if (st.trim().isNotEmpty())
                            textView.text = ("\nFrom Server : $st");
                    }
                }
                output.close()
                out.close()
                s.close()
            } catch (e: IOException) {
                e.printStackTrace();
            }
        })
        thread.start();
    }


    companion object {
        fun newInstance() = ClientFragment().apply {
            arguments = bundleOf()
        }
    }
}