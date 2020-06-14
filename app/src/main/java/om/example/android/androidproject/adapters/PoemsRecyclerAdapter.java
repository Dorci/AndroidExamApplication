package om.example.android.androidproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import om.example.android.androidproject.utility.RandomColourGenerator;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import om.example.android.androidproject.R;
import om.example.android.androidproject.model.Poem;

public class PoemsRecyclerAdapter extends RecyclerView.Adapter<PoemsRecyclerAdapter.ViewHolder>
{
    final private OnPoemClickListener poemClickListener;

    private ArrayList<Poem> poems;

    public PoemsRecyclerAdapter( OnPoemClickListener listener)
    {
        this.poems= new ArrayList<>();
        this.poemClickListener = listener;
    }

    public void setPoems(ArrayList<Poem> poems) {

        this.poems = poems;
    }

    @NonNull
    @Override
    public PoemsRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoemsRecyclerAdapter.ViewHolder holder, int position) {
        holder.poemText.setText(poems.get(position).getText());
        holder.author.setText(poems.get(position).getAuthor());

    }

    @Override
    public int getItemCount() {
        return  poems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView poemText;
        TextView author;
        ConstraintLayout background;
        RandomColourGenerator randomColourGenerator = new RandomColourGenerator();

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poemText = itemView.findViewById(R.id.poemTextView);
            author = itemView.findViewById(R.id.authorTextView);
            background = itemView.findViewById(R.id.single_item_recycler_constraintLayout);
            background.setBackgroundColor(randomColourGenerator.getColor());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            poemClickListener.onPoemClick(getAdapterPosition());
        }
    }

    public interface OnPoemClickListener {
        void onPoemClick(int clickedPoemIndex);
    }

}
