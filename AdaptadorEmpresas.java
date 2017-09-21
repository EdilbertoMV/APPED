package app.oneapp.eddy.myapp.com.oneapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorEmpresas extends RecyclerView.Adapter<AdaptadorEmpresas.ViewHolderEmpresas> implements View.OnClickListener{

    private ArrayList<Empresa> listaEmpresas;
    private View.OnClickListener listener;

    public AdaptadorEmpresas(ArrayList<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    @Override
    public ViewHolderEmpresas onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, null, false);

        view.setOnClickListener(this);

        return new ViewHolderEmpresas(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderEmpresas holder, int position) {

        holder.etNombre.setText(listaEmpresas.get(position).getNombre());
        holder.iImagen.setImageResource(listaEmpresas.get(position).getFoto());
       // holder.etMechar.setText(listaEmpresas.get(position).getMechardising());
       // holder.etEmisora.setText(listaEmpresas.get(position).getEmisora());
       // int codigo = listaEmpresas.get(position).getCodigo();
       // holder.etCodigo.setText(Integer.toString(codigo));

        //holder.ifoto.setImageResource(listaEmpresas.get(position).getFoto());

    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener = listener;
    }

    ////ON CLICK
    @Override
    public void onClick(View view) {

        if(listener!=null){
            listener.onClick(view);
        }

    }

    public class ViewHolderEmpresas extends RecyclerView.ViewHolder {

        TextView etNombre;
        TextView etMechar;
        TextView etEmisora;
        TextView etCodigo;
        ImageView iImagen;


        public ViewHolderEmpresas(View itemView) {
            super(itemView);

            etNombre = (TextView) itemView.findViewById(R.id.idNombre);
            //etMechar = (TextView) itemView.findViewById(R.id.idMechar);
            //etEmisora = (TextView) itemView.findViewById(R.id.idEmi);
            //etCodigo = (TextView) itemView.findViewById(R.id.idCod);

            iImagen= (ImageView) itemView.findViewById(R.id.idImagen);

        }
    }
}
