package vn.edu.hust.studentman

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
  private val students: List<StudentModel>,
  private val onEdit: (StudentModel) -> Unit,
  private val onDelete: (StudentModel) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

  var selectedPosition: Int = -1  // Biến lưu vị trí của mục được chọn trong context menu

  inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnCreateContextMenuListener {
    val textStudentName: TextView = itemView.findViewById(R.id.text_student_name)
    val textStudentId: TextView = itemView.findViewById(R.id.text_student_id)
    val imageEdit: ImageView = itemView.findViewById(R.id.image_edit)
    val imageRemove: ImageView = itemView.findViewById(R.id.image_remove)

    fun bind(student: StudentModel) {
      textStudentName.text = student.studentName
      textStudentId.text = student.studentId

      // Xử lý sự kiện nhấn vào nút chỉnh sửa và xóa
      imageEdit.setOnClickListener { onEdit(student) }
      imageRemove.setOnClickListener { onDelete(student) }

      // Đăng ký context menu cho itemView
      itemView.setOnCreateContextMenuListener(this)

      // Lưu vị trí của item được chọn
      itemView.setOnLongClickListener {
        selectedPosition = adapterPosition
        false // Trả về false để hiển thị context menu
      }
    }

    // Tạo context menu
    override fun onCreateContextMenu(menu: android.view.ContextMenu?, v: View?, menuInfo: android.view.ContextMenu.ContextMenuInfo?) {
      // Context menu được tạo trong MainActivity
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_item, parent, false)
    return StudentViewHolder(itemView)
  }

  override fun getItemCount(): Int = students.size

  override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
    val student = students[position]
    holder.bind(student)
  }
}
