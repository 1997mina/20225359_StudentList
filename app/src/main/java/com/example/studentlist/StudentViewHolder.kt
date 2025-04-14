import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentlist.R

class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvName: TextView = itemView.findViewById(R.id.tvName)
    val tvMSSV: TextView = itemView.findViewById(R.id.tvMSSV)
    val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
}