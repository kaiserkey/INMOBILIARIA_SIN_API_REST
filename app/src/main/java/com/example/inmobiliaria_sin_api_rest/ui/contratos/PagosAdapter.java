package com.example.inmobiliaria_sin_api_rest.ui.contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inmobiliaria_sin_api_rest.R;
import com.example.inmobiliaria_sin_api_rest.modelo.Pago;

import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Pago> pagosAsociados;
    private LayoutInflater inflater;

    public PagosAdapter(Context context, ArrayList<Pago> pagosAsociados, LayoutInflater inflater) {
        this.context = context;
        this.pagosAsociados = pagosAsociados;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public PagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = inflater.inflate(R.layout.item_pago, parent, false);
        return new PagosAdapter.ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull PagosAdapter.ViewHolder holder, int position) {
        holder.idPago.setText(String.valueOf(pagosAsociados.get(position).getIdPago()));
        holder.numPago.setText(String.valueOf(pagosAsociados.get(position).getNumero()));
        holder.codigoContrato.setText(String.valueOf(pagosAsociados.get(position).getContrato().getIdContrato()));
        holder.importe.setText(String.valueOf(pagosAsociados.get(position).getImporte()));
        holder.fechaDePago.setText(pagosAsociados.get(position).getFechaDePago());
    }

    @Override
    public int getItemCount() {
        return pagosAsociados.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView idPago,numPago, codigoContrato, importe, fechaDePago;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            idPago = itemView.findViewById(R.id.tvCodigoPago);
            numPago = itemView.findViewById(R.id.tvNumPago);
            codigoContrato = itemView.findViewById(R.id.tvCodigoContrato);
            importe = itemView.findViewById(R.id.tvImporte);
            fechaDePago = itemView.findViewById(R.id.tvFechaPago);

        }

    }

}
