package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  private TorpedoStore primary;
  private TorpedoStore secondary;
 
  @BeforeEach
  public void init(){
    this.primary = mock(TorpedoStore.class);
    this.secondary = mock( TorpedoStore.class);
    
    this.ship = new GT4500(primary, secondary);
    
    
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    


    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary,times(1)).fire(1);
    

  }

@Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    //verify(primary,times(2)).fire(1);
    //verify(secondary,times(2)).fire(1);
  }
  @Test
  public void fire_Torpedo_Secondary_Success(){
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary,times(0)).fire(1);
    verify(secondary,times(1)).fire(1);

  }
  @Test
  public void fire_Both_Empty_Succes(){
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);

    ship.fireTorpedo(FiringMode.SINGLE);
    
    verify(primary,times(0)).fire(1);
    verify(secondary,times(0)).fire(1);
  }
  @Test
  public void fire_Alternating_Success()
  {
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    ship.fireTorpedo(FiringMode.SINGLE);
    ship.fireTorpedo(FiringMode.SINGLE);

    verify(primary,times(1)).fire(1);
    verify(secondary,times(1)).fire(1);
  }

}
