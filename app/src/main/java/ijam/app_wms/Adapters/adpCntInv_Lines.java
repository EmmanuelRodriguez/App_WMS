package ijam.app_wms.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ijam.app_wms.Clases.clsDatos_OnHead;
import ijam.app_wms.Clases.clsDatos_OnLines;
import ijam.app_wms.R;

/**
 * Created by alberto.ramirez on 21/02/2017.
 */
public class adpCntInv_Lines extends BaseAdapter {
    Context oContext=null;
    LayoutInflater oInflanter;
    ArrayList<clsDatos_OnLines> oDts_OnLines;
    List<clsDatos_OnLines> oLstDts_OnLines;

    public adpCntInv_Lines(Context oContext, List<clsDatos_OnLines> oLstDts_OnLines) {
        this.oContext = oContext;
        this.oLstDts_OnLines = oLstDts_OnLines;
        oInflanter=LayoutInflater.from(oContext);
        this.oDts_OnLines= new ArrayList<clsDatos_OnLines>();
        this.oDts_OnLines.addAll(oLstDts_OnLines);
    }

    @Override
    public int getCount() {
        return oLstDts_OnLines.size();
    }

    public class ViewHolder {
        TextView lblDocEntry;
        TextView lblCodeBars;
        TextView lblItemName;
        TextView lblTipo;
        TextView lblQuantity;
        ImageView imgRow;
        ImageView imgStatus;
    }

    @Override
    public Object getItem(int position) {
        return oLstDts_OnLines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final  ViewHolder oHolder;

        if (convertView == null) {
            oHolder = new ViewHolder();
            convertView = oInflanter.inflate(R.layout.lst_frm_cntlines, null);
            oHolder.imgRow = (ImageView) convertView.findViewById(R.id.imgOn_Dtl);
            oHolder.lblDocEntry = (TextView) convertView.findViewById(R.id.lblCntOn_DtlDocEntry);
            oHolder.lblCodeBars = (TextView) convertView.findViewById(R.id.lblCntOn_DtlCodeBars);
            oHolder.lblItemName = (TextView) convertView.findViewById(R.id.lblCntOn_DtlItemName);
            oHolder.lblTipo = (TextView) convertView.findViewById(R.id.lblCntOn_DtlTipoCnt);
            oHolder.lblQuantity = (TextView) convertView.findViewById(R.id.lblCntOn_DtlQuantity);
            oHolder.imgStatus = (ImageView) convertView.findViewById(R.id.imgOn_DtlStatus);
            convertView.setTag(oHolder);
        } else {
            oHolder = (ViewHolder) convertView.getTag();
        }
        oHolder.imgRow.setImageResource(oLstDts_OnLines.get(position).getImagen());
        oHolder.lblDocEntry.setText(oLstDts_OnLines.get(position).getDocEntry());
        oHolder.lblCodeBars.setText(oLstDts_OnLines.get(position).getCodeBars());
        oHolder.lblItemName.setText(oLstDts_OnLines.get(position).getItemName());
     //   oHolder.lblTipo.setText(oLstDts_OnLines.get(position).getTipoCnt());

        if (Integer.parseInt(oLstDts_OnLines.get(position).getTipoCnt()) == 1){
            oHolder.lblTipo.setText("Simple");
        }
        else if(Integer.parseInt(oLstDts_OnLines.get(position).getTipoCnt()) == 2){
            oHolder.lblTipo.setText("Múltiple");
        }
        oHolder.lblQuantity.setText(oLstDts_OnLines.get(position).getQuantity());
        oHolder.imgStatus.setImageResource(oLstDts_OnLines.get(position).getImgStatus());

        return convertView;
    }

    public void Filtro(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        oLstDts_OnLines.clear();
        if (charText.length()==0 ){
            oLstDts_OnLines.addAll(oDts_OnLines);
        }else{
            for (clsDatos_OnLines oclD: oDts_OnLines){
                if(oclD.getCodeBars().toLowerCase(Locale.getDefault()).contains(charText)){
                    oLstDts_OnLines.add(oclD);
                }
            }
        }
        notifyDataSetChanged();

    }

}

