package businessLogic;
import java.io.File;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import dataAccess.DataAccess;
import domain.Sale;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.SaleAlreadyExistException;

import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	 private static final int baseSize = 160;

		private static final String basePath="src/main/resources/images/";
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager=new DataAccess();		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		dbManager=da;		
	}
    

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
	public Sale createSale(String title, String description,int status, float price, Date pubDate, String sellerEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException {
		dbManager.open();
		Sale product=dbManager.createSale(title, description, status, price, pubDate, sellerEmail, file);		
		dbManager.close();
		return product;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Sale> getSales(String desc){
		dbManager.open();
		List<Sale>  rides=dbManager.getSales(desc);
		dbManager.close();
		return rides;
	}
	
	/**
	    * {@inheritDoc}
	    */
		@WebMethod 
		public List<Sale> getPublishedSales(String desc, Date pubDate) {
			dbManager.open();
			List<Sale>  rides=dbManager.getPublishedSales(desc,pubDate);
			dbManager.close();
			return rides;
		}
	/**
	    * {@inheritDoc}
	    */
	@WebMethod public BufferedImage getFile(String fileName) {
		return dbManager.getFile(fileName);
	}

    
	public void close() {
		DataAccess dB4oManager=new DataAccess();
		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    /**
	 * {@inheritDoc}
	 */
    @WebMethod public Image downloadImage(String imageName) {
        File image = new File(basePath+imageName);
        try {
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //
    public boolean registerSeller(String name, String email, String password) {
		dbManager.open();
		boolean res = dbManager.registerSeller(name, email, password);
		dbManager.close();
		return res;
	}

	public boolean registerBuyer(String name, String email, String password) {
		dbManager.open();
		boolean res = dbManager.registerBuyer(name, email, password);
		dbManager.close();
		return res;
	}

	public Object login(String email, String password) {
		dbManager.open();
		Object user = dbManager.login(email, password);
		dbManager.close();
		return user;
	}

	public boolean acceptSale(String buyerEmail, Integer saleNumber) {
		dbManager.open();
		boolean res = dbManager.acceptSale(buyerEmail, saleNumber);
		dbManager.close();
		return res;
	}

	public List<domain.Sale> getAcceptedSales(String sellerEmail) {
		dbManager.open();
		List<domain.Sale> sales = dbManager.getAcceptedSales(sellerEmail);
		dbManager.close();
		return sales;
	}
	
	public java.util.List<domain.Sale> getActiveSalesByTitle(String title) {
		dbManager.open(); 
		java.util.List<domain.Sale> list = dbManager.getActiveSalesByTitle(title);
		dbManager.close();
		return list;
	}
	
	// --- 1. Guardar Contraoferta ---
		public boolean makeCounterOffer(String buyerEmail, Integer saleNumber, float offeredPrice) {
			dbManager.open(); // Recuerda usar tu versión de open
			boolean res = dbManager.makeCounterOffer(buyerEmail, saleNumber, offeredPrice);
			dbManager.close();
			return res;
		}

		// --- 2. Obtener Contraofertas Pendientes ---
		public java.util.List<domain.CounterOffer> getPendingCounterOffers(String sellerEmail) {
			dbManager.open();
			java.util.List<domain.CounterOffer> list = dbManager.getPendingCounterOffers(sellerEmail);
			dbManager.close();
			return list;
		}

		// --- 3. Aceptar o Rechazar Contraoferta ---
		public boolean resolveCounterOffer(Integer counterOfferId, boolean accept) {
			dbManager.open();
			boolean res = dbManager.resolveCounterOffer(counterOfferId, accept);
			dbManager.close();
			return res;
		}
}

