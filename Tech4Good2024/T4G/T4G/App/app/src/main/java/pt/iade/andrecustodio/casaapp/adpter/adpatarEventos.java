package pt.iade.andrecustodio.casaapp.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pt.iade.andrecustodio.casaapp.Modelos.Eventos;
import pt.iade.andrecustodio.casaapp.R;

public class adpatarEventos extends RecyclerView.Adapter<adpatarEventos.ViewHolder> {
    private ArrayList<Eventos> itemsevents;
    private RelativeLayout pnlEvento;
    private TextView txtNomeRua;
    private ImageView imgs;
    private TextView txtDataEvento;
    private LayoutInflater inflatercar;
    private ItemClickListener clickListenercar;

    public adpatarEventos(Context context, ArrayList<Eventos> items) {
        inflatercar = LayoutInflater.from(context);
        this.itemsevents = items;
        clickListenercar = null;
    }

    /**
     * Sets the event listener when a row gets clicked by the user.
     *
     * @param listener Event handler for the click.
     */
    public void setOnClickListener(ItemClickListener listener) {
        clickListenercar = listener;
    }

    /**
     * Inflates the layout of the row into the actual list.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return Instantiated row layout.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflatercar.inflate(R.layout.row_eventos, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds the data from each item in the list to a row in the list.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Eventos item = itemsevents.get(position);

        //Picasso.get().load(item.getUrlImg()).into(holder.imgs);

        holder.txtNomeRua.setText(item.getNomeRua());
        holder.txtDataEvento.setText(item.getDataEvento());
    }

    /**
     * The RecyclerView needs to know the size of our list, this just provides that.
     *
     * @return Size of our data.
     */
    @Override
    public int getItemCount() {
        return itemsevents.size();
    }

    /**
     * Class responsible for recycling the views as you scroll.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ArrayList<Eventos> itemsevents;
        private RelativeLayout pnlEvento;
        private TextView txtNomeRua;
        private ImageView imgs;
        private TextView txtDataEvento;


        /**
         * Sets up the view that was inflated.
         *
         * @param itemView Layout view that was inflated.
         */
        public ViewHolder(View itemView) {
            super(itemView);

            // Get the components in the view.
            pnlEvento = itemView.findViewById(R.id.pnlEvento);
            txtNomeRua = itemView.findViewById(R.id.txtTitulo);
            imgs = itemView.findViewById(R.id.imgEvento);
            //inflater = itemView.findViewById(R.id.pnlCar);
            txtDataEvento = itemView.findViewById(R.id.txtMensagem);

            // Set what happens when the view gets clicked.
            itemView.setOnClickListener(this);
        }

        /**
         * Handles the onclick event of the view.
         */
        @Override
        public void onClick(View view) {
            // Pass the event to our custom listener in the activity.
            if (clickListenercar != null) {
                clickListenercar.onItemClick(view, getAdapterPosition());
            }
        }
    }

    /**
     * Defines what to do when a list item gets clicked.
     */
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
