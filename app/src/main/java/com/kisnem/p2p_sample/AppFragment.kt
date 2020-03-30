package com.kisnem.p2p_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_app.*

class AppFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_client.setOnClickListener {
            openClientFragment()
        }

        btn_host.setOnClickListener {
            openHostFragment()
        }
    }

    private fun openClientFragment() {
        requireFragmentManager().beginTransaction().apply {
            replace(R.id.fl_container, ClientFragment.newInstance())
            addToBackStack("client")
            commit()
        }
    }

    private fun openHostFragment() {
        requireFragmentManager().beginTransaction().apply {
            replace(R.id.fl_container, HostFragment())
            addToBackStack("host")
            commit()
        }
    }

    companion object {
        fun newInstance() = AppFragment().apply {
            arguments = bundleOf()
        }
    }
}