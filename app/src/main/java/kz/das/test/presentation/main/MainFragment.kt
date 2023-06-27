package kz.das.test.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kz.das.test.data.model.MainResponse
import kz.das.test.databinding.FragmentMainBinding
import kz.das.test.domain.presentation.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!

    private val vm: MainFragmentViewModel by viewModel()

    private var adapter: MainAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {
        adapter = MainAdapter()
        binding.mainRecyclerView.adapter = adapter
    }

    private fun initObservers() {
        vm.viewState.observe(viewLifecycleOwner) {
            handleViewStateChanges(it)
        }
    }

    private fun handleViewStateChanges(viewState: ViewState<MainResponseViewState>) {
        if (ViewState.Loading != viewState)
            setScreenLoading(false)
        when (viewState) {
            is ViewState.Data -> handleHistoryNotificationViewStateChanges(viewState.data)
            is ViewState.Loading -> setScreenLoading(true)
            is ViewState.NetworkError -> showError()
            is ViewState.Error -> showError(viewState.error)
        }
    }

    private fun showError(error: String = "Unknown error") {
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
    }

    private fun setScreenLoading(isVisible : Boolean) = with(binding) {
        loader.isVisible = isVisible
    }

    private fun handleHistoryNotificationViewStateChanges(state: MainResponseViewState) {
        setScreenLoading(false)
        when (state) {
            is MainResponseViewState.OnImagesFetched -> onImageListFetched(
                state.photoList
            )
        }
    }

    private fun onImageListFetched(notificationList: List<MainResponse>) {
        adapter?.setData(notificationList)
    }

}