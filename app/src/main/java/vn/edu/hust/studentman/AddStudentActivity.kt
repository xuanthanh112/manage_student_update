package vn.edu.hust.studentman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val studentNameEditText = findViewById<EditText>(R.id.edit_student_name)
        val studentIdEditText = findViewById<EditText>(R.id.edit_student_id)
        val btnAddStudent = findViewById<Button>(R.id.btn_add_student)

        btnAddStudent.setOnClickListener {
            val name = studentNameEditText.text.toString()
            val id = studentIdEditText.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty()) {
                // Trả kết quả về MainActivity
                val resultIntent = Intent()
                resultIntent.putExtra("STUDENT_NAME", name)
                resultIntent.putExtra("STUDENT_ID", id)
                setResult(RESULT_OK, resultIntent)

                finish()  // Đóng Activity
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
