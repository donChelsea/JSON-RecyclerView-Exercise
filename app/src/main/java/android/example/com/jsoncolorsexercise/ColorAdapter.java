package android.example.com.jsoncolorsexercise;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHolder> {
    private List<Color> colorList;

    public ColorAdapter(List<Color> colorList) {
        this.colorList = colorList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name, category, type, code;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.color_name);
            category = (TextView) itemView.findViewById(R.id.color_category);
            type = (TextView) itemView.findViewById(R.id.color_type);
            code = (TextView) itemView.findViewById(R.id.color_code);
        }
    }

    @NonNull
    @Override
    public ColorAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.color_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.MyViewHolder holder, int position) {
        Color color = colorList.get(position);
        holder.name.setText(color.getColor());
        holder.category.setText(color.getCategory());
        holder.type.setText(color.getType());
        holder.code.setText((CharSequence) color.getCode());
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

}
