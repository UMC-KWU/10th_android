package com.neouul.umc10android.week03.presentation.fragment.recycler

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neouul.umc10android.week03.R
import com.neouul.umc10android.week03.databinding.FragmentFriendListBinding
import com.neouul.umc10android.week03.domain.model.FriendData
import com.neouul.umc10android.week03.presentation.screen.recycler.FriendAdapter

class FriendListFragment : Fragment(R.layout.fragment_friend_list) {

    private var _binding: FragmentFriendListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFriendListBinding.bind(view)

        // 1. 표시할 데이터 목록 생성
        val friendDataList = mutableListOf<FriendData>(
            FriendData("김철수", "안녕! 반가워", null),
            FriendData("이영희", "오늘도 화이팅!", null),
            FriendData("박민수", "열공 중...", null),
            FriendData("최지우", "커피 한 잔의 여유", null),
            FriendData("정다은", "날씨가 좋네요", null),
            FriendData("김철수2", "안녕! 반가워", null),
            FriendData("이영희2", "오늘도 화이팅!", null),
            FriendData("박민수2", "열공 중...", null),
            FriendData("최지우2", "커피 한 잔의 여유", null),
            FriendData("정다은2", "날씨가 좋네요", null),
        )

        // 2. 어댑터 인스턴스 생성 및 RecyclerView에 연결
        val adapter = FriendAdapter(
            friendDataList,
            onVisitClicked = { friend ->
                Toast.makeText(context, "${friend.name}에게 방문하기", Toast.LENGTH_SHORT).show()
            })

        // 3. 어댑터 연결 및 LayoutManager 설정
        binding.friendRecyclerview.adapter = adapter
        binding.friendRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}