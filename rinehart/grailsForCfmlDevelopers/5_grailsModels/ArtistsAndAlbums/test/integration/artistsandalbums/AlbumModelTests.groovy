package artistsandalbums

import static org.junit.Assert.*
import org.junit.*

class AlbumModelTests {

    @Test
    void our_relationships_work() {
		def album = new Album( name: "Alive and Wired", songs : [] )
		
		album.save()
		
		def song = new Song(
			name : "Rollerskate Skinny"
		)
		assert song.album == album
		
		assert album.songs.size() == 0
		album.addToSongs( song )
		assert album.songs.size() > 0
		
		album.save()
		song.save()
		
		assert Song.list().size() > 0

		/*		
		def artist = new Artist( name : "Old 97s" ).save()
		
		artist.addToAlbums( artist )
		artist.save()
		
		assert song.album.artists.contains( artist )
		*/
		
		
		
    }
}
