import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.MainActivity
import com.example.studentlist.R

class StudentAdapter(
    private var students: MutableList<MainActivity.Student>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.tvName.text = student.name
        holder.tvMSSV.text = student.mssv

        holder.btnDelete.setOnClickListener {
            Log.d("DELETE", "Clicked position: ${holder.absoluteAdapterPosition}")
            onDeleteClick(holder.absoluteAdapterPosition)
        }
    }

    override fun getItemCount(): Int = students.size

    fun updateData(newStudents: MutableList<MainActivity.Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}