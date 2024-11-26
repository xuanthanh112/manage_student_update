package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private lateinit var students: MutableList<StudentModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val studentId = intent.getStringExtra("STUDENT_ID")
        students = intent.getParcelableArrayListExtra<StudentModel>("STUDENT_LIST")?.toMutableList() ?: mutableListOf()

        val studentNameEditText = findViewById<EditText>(R.id.edit_student_name)
        val studentIdEditText = findViewById<EditText>(R.id.edit_student_id)
        val btnSaveChanges = findViewById<Button>(R.id.btn_add_student)

        // Lấy thông tin sinh viên cần sửa
        val student = getStudentById(studentId)
        studentNameEditText.setText(student?.studentName)
        studentIdEditText.setText(student?.studentId)

        btnSaveChanges.setOnClickListener {
            val name = studentNameEditText.text.toString()
            val id = studentIdEditText.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                // Trả lại kết quả cho MainActivity
                val resultIntent = Intent()
                resultIntent.putExtra("STUDENT_ID", id)
                resultIntent.putExtra("STUDENT_NAME", name)
                setResult(RESULT_OK, resultIntent)

                finish()  // Đóng Activity
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Giả sử bạn có phương thức lấy sinh viên từ ID
    private fun getStudentById(studentId: String?): StudentModel? {
        // Thực hiện tìm sinh viên theo ID từ danh sách của bạn
        return students.find { it.studentId == studentId }
    }
}