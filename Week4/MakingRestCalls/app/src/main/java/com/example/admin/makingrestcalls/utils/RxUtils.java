package com.example.admin.makingrestcalls.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

import com.example.admin.makingrestcalls.model.APIResponses.example.APIResponse;
import com.example.admin.makingrestcalls.model.APIResponses.example.Result;
import com.example.admin.makingrestcalls.model.CustomUser;

import java.util.Random;

public class RxUtils {

 public static Function<APIResponse,CustomUser> getMappingFunction(){
   return  new io.reactivex.functions.Function<APIResponse, CustomUser>(){

         @Override
         public CustomUser apply(APIResponse apiResponse) throws Exception {
             return new CustomUser(apiResponse,"Engineering","Atlanta");
         }
     };
 }

 public static Function<APIResponse, Observable<Result>> getResultMapper() {

     Function<APIResponse, Observable<Result>> function = new Function<APIResponse, Observable<Result>>() {
         @Override
         public Observable<Result> apply(final APIResponse apiResponse) throws Exception {

             //create observable
             Observable<Result> observable = Observable.create(new ObservableOnSubscribe<Result>() {
                 @Override
                 public void subscribe(ObservableEmitter<Result> emitter) throws Exception {
                for (Result result : apiResponse.getResults()) {
                         emitter.onNext(result);
                     }
                     emitter.onComplete();
                 }
             });
             return observable;
         }


     };
     return function;
 }

 public static Function<Result,Result> transformResult(){
     Function<Result,Result> function =new Function<Result, Result>() {
         @Override
         public Result apply(Result result) throws Exception {

             
             String newLast= result.getName().getLast()+"  "+getRandomInt();

             result.getName().setLast(newLast);
             return result;
         }
     };
     return function;
     }



     public static int getRandomInt(){
     Random random =new Random();
     return random.nextInt();
     }


}
