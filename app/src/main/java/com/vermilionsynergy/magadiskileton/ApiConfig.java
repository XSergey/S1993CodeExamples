package com.vermilionsynergy.magadiskileton;
import com.vermilionsynergy.magadiskileton.ApiConfigList;
import com.vermilionsynergy.magadiskileton.ApiRequestHandler;

import android.util.Log;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class ApiConfig {

    public String httpPrefix = "http://";

    public String httpsPrefix = "https://";

    public String baseUrl = "45.55.56.69";

    public String prefixUrl = "/rest/api/v1";

    public String alias;

    public String query;

    public String[] resource;

    public String url;

    public ApiConfig(String alias) {

        this.alias = alias;

        ApiConfigList apibase = new ApiConfigList();
        this.resource = apibase.getResourceByAlias(alias);
    }

    public String buildRequest() {
        String _request = this.httpPrefix + this.baseUrl + this.prefixUrl + this.resource[0] + this.query;

        ApiRequestHandler handler = new ApiRequestHandler();
        String _response = null;

        try {
            handler.execute(_request).wait();
            _response = handler.getResponse();
        } catch (InterruptedException e) {
            _response = e.getMessage();
        //} catch (ExecutionException e) {
        //    _response = e.getMessage();
        }

        return _response;
    }

    public String getAlias() {
        return this.alias;
    }

    public String[] getResource(){
        return this.resource;
    }

    public int getResourceLength(){
        return this.resource.length;
    }

    public int getParamsLength(){
        return this.resource.length-1;
    }

    public void setParams(String[] input){
        String query = "?";
        String[] defaultParams = this.getParams();

        for(int i=0; i < input.length; i++){

            if(defaultParams[i] != null && input[i] != null)

                query += defaultParams[i].replace(":","")+"="+input[i];

                if(i < input.length-1)
                    query += "&";
        }

        this.query = query;
    }

    public String[] getParams(){
        String[] _params = this.resource;

        //remove first parameter as url
        List<String> newParams = new ArrayList<String>();
        for(int i=0; i < _params.length; i++){
            if(i != 0){
                newParams.add(_params[i]);
            }
        }
        return newParams.toArray(new String[newParams.size()]);
    }

    public String getJsonRequest(String uri, String type) {

        return "";
    }

}
