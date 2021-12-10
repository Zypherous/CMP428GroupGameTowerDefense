
public interface Damage 
{
	
	
	/*public Damage(int damage, int health) // would take in the health of an object and damage
	{
		this.health = health;
		this.damage = damage;
	}
	*/
	public void takeDamage(int damage); //health of object gets subtracted by damage of an object
	
	
}
/* 
 Damage(enemy.Damage, tower.health);

tower.takeDamage();
*/