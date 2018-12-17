package br.mackenzie.easymarket.communication;

import android.location.Address;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

import br.mackenzie.easymarket.Register;

public class AddressRequest extends AsyncTask<Void, Void, br.mackenzie.easymarket.modelo.Address> {
    private WeakReference<Register> activity;

    public AddressRequest( Register activity ){
        this.activity = new WeakReference<>( activity );
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.get().lockFields( true );
    }

    @Override
    protected br.mackenzie.easymarket.modelo.Address doInBackground(Void... voids) {

        try{
            String jsonString = JsonRequest.request( activity.get().getUriRequest() );
            Gson gson = new Gson();

            return gson.fromJson(jsonString, br.mackenzie.easymarket.modelo.Address.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(br.mackenzie.easymarket.modelo.Address address) {
        super.onPostExecute(address);

        if( activity.get() != null ){
            activity.get().lockFields( false );

            if( address != null ){
                activity.get().setAddressFields(address);
            }
        }
    }
}
