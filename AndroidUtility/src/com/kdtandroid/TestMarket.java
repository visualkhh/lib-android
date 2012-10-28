//package com.kdtandroid;
//
//import com.gc.android.market.api.MarketSession;
//import com.gc.android.market.api.MarketSession.Callback;
//import com.gc.android.market.api.model.Market.AppsRequest;
//import com.gc.android.market.api.model.Market.AppsResponse;
//import com.gc.android.market.api.model.Market.ResponseContext;
//
//
//public class TestMarket {
//
//	public static void main(String[] args) {
//		System.out.println("------------------------start--------------------------");
//        try {
//            
//            MarketSession session = new MarketSession(); 
//            session.getContext ().setAndroidId ( "3000000000000000");
//            session.login("@gmail.com", "");
//            String query = "maps";
//            
////            AppsRequest appsRequest = AppsRequest.newBuilder()
////             .setQuery(query)
////             .setStartIndex(0)
////             .setEntriesCount(10)
////             .setOrderType(AppsRequest.OrderType.NEWEST)
////             .setWithExtendedInfo(true).build();
//            AppsRequest appsRequest = AppsRequest.newBuilder()
//                                            .setQuery(query)
//                                            .setStartIndex(0).setEntriesCount(10)
//                                            .setWithExtendedInfo(true)
//                                            .build();
//            
//            session.append(appsRequest, new Callback<AppsResponse>() {
//             @Override          
//             public void onResult(ResponseContext context, AppsResponse response){
//              // Your code here
//              // response.getApp(0).getCreator() ...
//              // see AppsResponse class definition for more infos
//              System.out.println(response.getApp(0).getTitle());
//              System.out.println(response.getApp(0).getCreator());
//              System.out.println(response.getApp(0).getRating());
//              System.out.println(response.getApp(0).getRatingsCount());
//              System.out.println(response.getApp(0).getPrice());
//              
//              System.out.println(response.getApp(0).getPriceCurrency());
//              System.out.println(response.getApp(0).getPriceMicros());
//              System.out.println(response.getApp(0).getSerializedSize());
//              System.out.println(response.getApp(0).getVersion());
//              System.out.println(response.getApp(0).getExtendedInfo().getDownloadsCountText());
//             }
//            }
//            ); 
//            
//            session.flush();
//           } catch (Exception e) {
//            e.printStackTrace();
//           }
//           System.out.println("------------------------end--------------------------");
//	}
//}